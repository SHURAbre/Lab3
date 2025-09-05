public class CriterioPrecoMaximo implements CriterioBusca {
    @Override
    public boolean testar(Produto p, String valor) {
        try {
            double precoMax = Double.parseDouble(valor);
            return p.getPreco() <= precoMax;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}