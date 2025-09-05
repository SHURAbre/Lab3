public class CriterioPrecoMinimo implements CriterioBusca {
    @Override
    public boolean testar(Produto p, String valor) {
        try {
            double precoMin = Double.parseDouble(valor);
            return p.getPreco() >= precoMin;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}