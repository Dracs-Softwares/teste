package DAO;

import conexoes.ConexaoMySql;
import java.util.ArrayList;
import model.ModelProdutos;

public class DaoProdutos extends ConexaoMySql {

    /**
     * cadastrar um produto do banco
     *
     * @param pModelProdutos
     * @return
     */
    public int salvarProdutosDAO(ModelProdutos pModelProdutos) {

        try {
            this.conectar();
            return this.insertSQL("INSERT INTO produto("
                    + "nome,"
                    + "valor,"
                    + "estoque"
                    + ") VALUES ("
                    + "'" + pModelProdutos.getProNome() + "',"
                    + "'" + pModelProdutos.getProValor() + "',"
                    + "'" + pModelProdutos.getProEstoque() + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * exclui um poduto do banco
     *
     * @param pIdProduto
     * @return boolean
     */
    public boolean excluirProdutoDAO(int pIdProduto) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM produto WHERE idproduto = '" + pIdProduto + "' "
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * alterar dados do produto
     *
     * @param pModelProdutos
     * @return
     */
    public boolean alterarProdutoDAO(ModelProdutos pModelProdutos) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE produto SET"
                    + "nome = '" + pModelProdutos.getProNome() + "'"
                    + "valor = '" + pModelProdutos.getProValor() + "'"
                    + "estoque = '" + pModelProdutos.getProEstoque() + "'"
                    + "WHERE idproduto = '" + pModelProdutos.getIdProduto() + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
    /**
     * retornar um prosuto pelo código
     * @param pIdProduto
     * @return ModelProduto
     */
    public ModelProdutos retornarProdutoDAO(int pIdProduto) {
        ModelProdutos modelProdutos = new ModelProdutos();

        try {
            this.conectar();
            this.executarSQL("SELECT"
                    + "idproduto,"
                    + "nome,"
                    + "valor,"
                    + "estoque,"
                    + "FROM produto WHERE idproduto = '" + pIdProduto + "';"
            );

            while (this.getResultSet().next()) {
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));
                modelProdutos.setProNome(this.getResultSet().getString(2));
                modelProdutos.setProValor(this.getResultSet().getDouble(3));
                modelProdutos.setProEstoque(this.getResultSet().getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelProdutos;
    }
    /**
     * retornar uma lista completa de produtos
     * @return listaModelProdutos
     */
    public ArrayList<ModelProdutos> retornarListaProdutosDAO() {
        ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<>();
        ModelProdutos modelProdutos = new ModelProdutos();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "idproduto, "
                    + "nome,"
                    + "valor,"
                    + "estoque "
                    + "FROM produto;");

            while (this.getResultSet().next()) {
                modelProdutos = new ModelProdutos();
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));
                modelProdutos.setProNome(this.getResultSet().getString(2));
                modelProdutos.setProValor(this.getResultSet().getDouble(3));
                modelProdutos.setProEstoque(this.getResultSet().getInt(4));
                listaModelProdutos.add(modelProdutos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelProdutos;
    }

    /**
     * Alterar estoque de produtos no banco
     * @param pListaModelProdutoses
     * @return 
     */
    public boolean alterarEstoqueProdutosDAO(ArrayList<ModelProdutos> pListaModelProdutoses) {
        try {
            this.conectar();
            for (int i = 0; i < pListaModelProdutoses.size(); i++) {
                this.executarUpdateDeleteSQL(
                        "UPDATE produto SET "
                        + "estoque = '" + pListaModelProdutoses.get(i).getProEstoque()+ "'"
                        + " WHERE idproduto = '" + pListaModelProdutoses.get(i).getIdProduto() + "'"
                );
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

}
