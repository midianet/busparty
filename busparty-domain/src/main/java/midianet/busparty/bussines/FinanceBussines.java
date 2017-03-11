package midianet.busparty.bussines;

import midianet.busparty.domain.Payment;
import midianet.busparty.domain.Person;
import midianet.busparty.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FinanceBussines {

    @Autowired
    private PaymentRepository paymentRepository;

//    @Autowired
//    private ParametroRepository parametroRepository;
//
//    @Autowired
//    private QuartoRepository quartoRepository;

    public List<Payment> listPaymentsDoneByPerson(final Person person){
        return paymentRepository.listDoneByPerson(person.getId());
    }

//    public List<Cost> listDespesaByPessoa(final Person pessoa) {
//        List<Cost> list = new ArrayList();
//        pessoa.setBedroom(quartoRepository.findById(pessoa.getBedroom().getId()).get());
//        Optional<Parameter> parameter = parametroRepository.findByChave("CARAVANA");
//        parameter.ifPresent(p -> {
//            final Cost ds = new Cost();
//            ds.setDescription(p.getDescription());
//            ds.setAmmount(Double.parseDouble(p.getValue()));
//            list.add(ds);
//        });
//        Cost d = new Cost();
//        d.setDescription(pessoa.getBedroom().getType().getDescription());
//        d.setAmmount(pessoa.getBedroom().getType().getAmmount());
//        list.add(d);
//
//        createDespesa("GELATINA").ifPresent( ds -> list.add(ds));
//        createDespesa("ALMMOTOR").ifPresent( ds -> list.add(ds));
//        createDespesa("AGUA").ifPresent( ds -> list.add(ds));
//
//        boolean gelo = false;
//        if (pessoa.isRefrigerante()) {
//            gelo = true;
//            createDespesa("REFRIGERANTE").ifPresent(ds -> list.add(ds));
//        }
//        if (pessoa.isCerveja()) {
//            gelo = true;
//            createDespesa("CERVEJA").ifPresent(ds -> list.add(ds));
//        }
//        if (pessoa.isIce()) {
//            gelo = true;
//            createDespesa("ICE").ifPresent(ds -> list.add(ds));
//        }
//        if (pessoa.isSuco()) {
//            gelo = true;
//            createDespesa("SUCO").ifPresent(ds -> list.add(ds));
//        }
//        if (pessoa.isEnergetico()) {
//            gelo = true;
//            createDespesa("ENERGETICO").ifPresent(ds -> list.add(ds));
//        }
//        if (pessoa.isToddynho()) {
//            gelo = true;
//            createDespesa("TODDYNHO").ifPresent(ds -> list.add(ds));
//        }
//        if (pessoa.isAguaCoco()) {
//            gelo = true;
//            createDespesa("AGUA_COCO").ifPresent(ds -> list.add(ds));
//        }
//        if (gelo){
//            createDespesa("GELO").ifPresent(ds -> list.add(ds));
//         }
//        return list;
//    }

//    private Optional<Cost> createDespesa(final String descricao){
//        final Optional<Parameter> parameter = parametroRepository.findByChave(descricao);
//        Optional<Cost> desp = Optional.empty();
//        if(parameter.isPresent()){
//            final Cost ds = new Cost();
//            ds.setDescription(parameter.get().getDescription());
//            ds.setAmmount(Double.parseDouble(parameter.get().getValue()));
//            desp = Optional.of(ds);
//        }
//        return desp;
//    }
}