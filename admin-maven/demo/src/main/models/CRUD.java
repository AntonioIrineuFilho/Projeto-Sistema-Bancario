package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public interface VerificaId {
    void setId(int id);
    int getId();
}

public class CRUD<TipoObjeto extends VerificaId> {

    private List<TipoObjeto> listaObjetos;
    private final String arquivoJson;

    public CRUD(String arquivoJson) {
        this.listaObjetos = new ArrayList<>();
        this.arquivoJson = arquivoJson;
    }

    public void inserir(TipoObjeto objeto) {
        abrir();
        int id = 0;
        if (listaObjetos.size() != 0) {
            for (int i = 0; i < listaObjetos.size(); i++) {
                if (listaObjetos.get(i).getId() > id) {
                    id = listaObjetos.get(i).getId();
                }
            }
        } else { id = -1; }
        objeto.setId(id+1);
        listaObjetos.add(objeto);
        salvar();
    }

    public List<TipoObjeto> listar() {
        abrir();
        return listaObjetos;
    }

    public boolean atualizar(int id, TipoObjeto novoObjeto) {
        abrir();
        for (int i = 0; i < listaObjetos.size(); i++) {
            if (listaObjetos.get(i).getId() == id) {
                listaObjetos.set(i, novoObjeto);
                salvar();
                return true;
            }
        }
        return false;
    }

    public boolean deletar(int id) {
        abrir();
        for (int i = 0; i < listaObjetos.size(); i++) {
            if (listaObjetos.get(i).getId() == id) {
                listaObjetos.remove(i);
                salvar();
                return true;
            }
        }
        return false;
    }

    private void salvar() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(arquivoJson)) {
            gson.toJson(listaObjetos, writer);
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }

    private void abrir() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(arquivoJson)) {
            Type tipoObjeto = new TypeToken<List<TipoObjeto>>() {}.getType();
            List<TipoObjeto> objetos = gson.fromJson(reader, tipoObjeto);
            if (objetos != null) {
                listaObjetos = objetos;
            }
        } catch (IOException erro) {
            System.err.println("Erro ao carregar dados do JSON para a lista: " + erro.getMessage());
        }
    }

}