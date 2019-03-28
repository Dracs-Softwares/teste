/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.DaoProdutos;
import model.ModelProdutos;
import java.util.ArrayList;

/**
 *
 * @author JDANIEL
 */
public class ControllerProdutos {

    private DaoProdutos daoProdutos = new DaoProdutos();

    /**
     * salvar produtos controller
     *
     * @param pModelProdutos
     * @return
     */
    public int salvarProdutosController(ModelProdutos pModelProdutos) {
        return this.daoProdutos.salvarProdutosDAO(pModelProdutos);
    }

    /**
     * excluir produto pelo codigo
     *
     * @param pCodigo
     * @return
     */
    public boolean excluirProdutoController(int pCodigo) {
        return this.daoProdutos.excluirProdutoDAO(pCodigo);
    }
    
    /**
     * alterar um produto
     * @param pModelProdutos
     * @return 
     */
    public boolean alterarProdutoController(ModelProdutos pModelProdutos) {
        return this.daoProdutos.alterarProdutoDAO(pModelProdutos);
    }
    
    /**
     * retornar poduto pelo codigo
     * @param pCodigo
     * @return 
     */
    public ModelProdutos retornarProdutoController(int pCodigo){
        return this.daoProdutos.retornarProdutoDAO(pCodigo);
    }
    
    /**
     * retonar uma lista de podutos
     * @return  lista Model Produtos
     */
    public ArrayList<ModelProdutos> retornarListaProdutoContoler(){
        return this.daoProdutos.retornarListaProdutosDAO();
    }
    
}
