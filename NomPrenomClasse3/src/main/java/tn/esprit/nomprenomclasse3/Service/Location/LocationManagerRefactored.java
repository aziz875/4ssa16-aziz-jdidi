package tn.esprit.nomprenomclasse3.Service.Location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nomprenomclasse3.Domain.Contrat;
import tn.esprit.nomprenomclasse3.Domain.ModePaiement;
import tn.esprit.nomprenomclasse3.Domain.Paiement;
import tn.esprit.nomprenomclasse3.Domain.Reservation;
import tn.esprit.nomprenomclasse3.Repository.ContratRepository;
import tn.esprit.nomprenomclasse3.Repository.ReservationRepository;
import tn.esprit.nomprenomclasse3.Service.Location.Legacy.SmsGateway;
import tn.esprit.nomprenomclasse3.Service.Location.Legacy.SmtpClient;
import tn.esprit.nomprenomclasse3.Service.Location.Paiement.PaiementProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Version refactorisée de LocationManager appliquant le principe OCP (Mission 2).
 * Le bloc de paiement ne contient plus de if/else : il délègue au PaiementProcessor.
 */
@Service
@RequiredArgsConstructor
public class LocationManagerRefactored {

    private final ReservationRepository reservationRepository;
    private final ContratRepository contratRepository;
    private final PaiementProcessor paiementProcessor;

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

        // ---------- 4. paiement (REFACTORISÉ CONFORME OCP) ----------
        // Plus aucun if/else ! Délégation polymorphique à la stratégie appropriée.
        ModePaiement mode = ModePaiement.valueOf(modePaiement.toUpperCase());
        Paiement p = paiementProcessor.executerPaiement(mode, m);
        m = p.getMontant().doubleValue();

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
