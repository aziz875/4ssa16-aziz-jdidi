package tn.esprit.nomprenomclasse3.Service.Location.Legacy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.nomprenomclasse3.Domain.Contrat;
import tn.esprit.nomprenomclasse3.Domain.ModePaiement;
import tn.esprit.nomprenomclasse3.Domain.Paiement;
import tn.esprit.nomprenomclasse3.Domain.Reservation;
import tn.esprit.nomprenomclasse3.Repository.ContratRepository;
import tn.esprit.nomprenomclasse3.Repository.ReservationRepository;

/**
 * Confirmation des reservations.
 * Ecrit en urgence par le stagiaire. NE PAS MODIFIER : ca marche.
 * Fichier fourni pour l'Atelier 6.
 */
@Service
public class LocationManager {

    @Autowired private ReservationRepository reservationRepository;
    @Autowired private ContratRepository contratRepository;

    public Contrat confirmerReservation(Long reservationId, String modePaiement, String codePromo, String canal) {
        Reservation r = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation introuvable"));

        // ---------- 1. disponibilite ----------
        for (Reservation x : reservationRepository.findAll()) {
            if (x.getVehicule().getIdVehicule().equals(r.getVehicule().getIdVehicule())
                    && x.getIdReservation() != r.getIdReservation()
                    && !(r.getDateFin().isBefore(x.getDateDebut())
                    || r.getDateDebut().isAfter(x.getDateFin()))) {
                throw new RuntimeException("Vehicule indisponible");
            }
        }

        // ---------- 2. tarif de base ----------
        long j = ChronoUnit.DAYS.between(r.getDateDebut(), r.getDateFin()) + 1;
        double m = j * (r.getVehicule().getTarifJournalier() != null ? r.getVehicule().getTarifJournalier().doubleValue() : 50.0);

        // ---------- 3. remises ----------
        String t = r.getClient() != null && r.getClient().getType() != null ? r.getClient().getType() : "PARTICULIER";
        if (t.equals("PARTICULIER")) {
            if (j >= 7) m = m * 0.90;
        } else if (t.equals("ENTREPRISE")) {
            m = m * 0.85;
        } else if (t.equals("VIP")) {
            m = m * 0.80;
        }
        if (codePromo != null && codePromo.equals("BIENVENUE")) {
            m = m - 20;
        }

        // ---------- 4. paiement ----------
        Paiement p = new Paiement();
        p.setDatePaiement(LocalDate.now());
        if (modePaiement.equals("CARTE")) {
            System.out.println("Debit de la carte bancaire...");
            m = m * 1.01; // 1 % de frais
            p.setModePaiement(ModePaiement.CARTE);
        } else if (modePaiement.equals("VIREMENT")) {
            System.out.println("Attente du virement...");
            p.setModePaiement(ModePaiement.VIREMENT);
        } else if (modePaiement.equals("ESPECES")) {
            System.out.println("Encaissement en especes a l'agence");
            p.setModePaiement(ModePaiement.ESPECES);
        } else {
            throw new RuntimeException("Mode de paiement inconnu : " + modePaiement);
        }
        p.setMontant(BigDecimal.valueOf(m));

        // ---------- 5. contrat ----------
        Contrat c = new Contrat();
        c.setDateSignature(LocalDate.now());
        c.setMontantTotal(BigDecimal.valueOf(m));
        c.setValide(true);
        c.getPaiements().add(p);
        p.setContrat(c);
        Contrat res = contratRepository.save(c);

        // ---------- 6. notification ----------
        if (canal.equals("EMAIL")) {
            SmtpClient smtp = new SmtpClient("smtp.autoloc.tn", 25);
            smtp.send(r.getClient().getEmail(), "Contrat " + res.getIdContrat() + " confirme");
        } else if (canal.equals("SMS")) {
            SmsGateway sms = new SmsGateway("https://sms.autoloc.tn");
            sms.send(r.getClient().getTelephone(), "Contrat " + res.getIdContrat() + " confirme");
        }

        return res;
    }
}
