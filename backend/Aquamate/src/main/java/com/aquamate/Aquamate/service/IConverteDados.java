package com.aquamate.Aquamate.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
