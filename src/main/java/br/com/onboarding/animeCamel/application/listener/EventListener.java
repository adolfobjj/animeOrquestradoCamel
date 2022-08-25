package br.com.onboarding.animeCamel.application.listener;


import br.com.onboarding.animeCamel.application.listener.constantes.RabbitmqConstantes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class EventListener {

    //public static final String ANIME = "anime.save";
    public static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public EventListener(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;

    }

    //teste
    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);

    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona() {
        Queue filaAnime = this.fila(RabbitmqConstantes.FILA_ANIME);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoAnime = this.relacionamento(filaAnime, troca);

        this.amqpAdmin.declareQueue(filaAnime);
        this.amqpAdmin.declareExchange(troca);
        this.amqpAdmin.declareBinding(ligacaoAnime);

    }
}
   // @RabbitListener(queues = ANIME)
//    public void listenerTest(@Payload  String message) {
//        try {
//
//        } catch (Throwable e) {
//            log.error("Deu erro", e);
//        }
//    }
//}

