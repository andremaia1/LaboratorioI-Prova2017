package prova;

/**
 *
 * @author angeloluz
 */
public class Produto {

    private String marca;
    private String nome;
    private int volume;
    private double valorDeCompra;
    private double valorDeVenda;
    private double valorPorLitro;

    //Campos adicionais
    private int quantidade;

    public Produto() {

    }

    public Produto(String nome, String marca, int volume) {
        setNome(nome);
        setMarca(marca);
        setVolume(volume);
    }

    public void cadastrar (String marca, String nome, int volume, double valorDeCompra,
    double valorDeVenda, int quantidade){
        setMarca(marca);
        setNome(nome);
        setVolume(volume);
        setValorDeCompra(valorDeCompra);
        setValorDeVenda(valorDeVenda);
        setQuantidade(quantidade);
    }
    
    public boolean baixarDoEstoque(int quantidade){
        if (this.quantidade < quantidade){
            return false;
        }else{
            this.quantidade -= quantidade;
            return true;
        }
        
    }
    
    private void valorPorLitro() {
        valorPorLitro =  ((valorDeVenda / volume) * 1000);
    }

    public String getMarca() {
        return marca;
    }

    private void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public int getVolume() {
        return volume;
    }

    private void setVolume(int volume) {
        this.volume = volume;
    }

    public double getValorDeCompra() {
        return valorDeCompra;
    }

    private void setValorDeCompra(double valorDeCompra) {
        this.valorDeCompra = valorDeCompra;
    }

    public double getValorDeVenda() {
        return valorDeVenda;
    }

    private void setValorDeVenda(double valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
        valorPorLitro();
    }

    public double getValorPorLitro() {
        return valorPorLitro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    private void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * 
     * @return Retorna todos os atributos de forma a facilitar a manipulação (leitura e escrita) destes dados no arquivo
     */
    public String atributos () {
        return marca+" ; "+nome+" ; "+volume+" ; "+valorDeCompra+" ; "+valorDeVenda+" ; "+quantidade;
    }
    
    @Override
    public String toString() {
        //return "Produto{" + "marca=" + marca + ", nome=" + nome + ", volume=" + volume + "ml, valorDeCompra= R$" + valorDeCompra + ", valorDeVenda= R$" + valorDeVenda + ", valorPorLitro= R$" + valorPorLitro + ", quantidade=" + quantidade + '}';
        return String.format("Produto{ marca=%s, nome=%s, volume=%d ml, Valor de Compra= R$ %.2f, Valor de Venda = %.2f, Valor por litro = %.2f, Quantidade em estoque = %d'}'\n",marca, nome, volume, valorDeCompra,valorDeVenda, valorPorLitro, quantidade);
    }

}
