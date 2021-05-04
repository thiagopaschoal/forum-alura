package br.com.tspaschoal.forumalura.support;

import org.springframework.data.domain.Page;

public interface Converter<T, E> {
    public Page<T> converter(Page<E> values);
}
