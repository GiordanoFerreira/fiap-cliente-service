package br.com.postechfiap.fiap_cliente_service.interfaces;

public interface Adapter<Source, Destination> {

    Destination adapt(Source source);

    Destination adapt(Source source, Destination destination);
}
