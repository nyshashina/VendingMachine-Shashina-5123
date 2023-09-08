package Services;

import Domen.Product;


public class Holder {
    private Product[][] products;
    private int rows;
    private int columns;
    private int maxItemsPerCell;
    public Holder(int rows, int columns, int maxItemsPerCell){
        this.rows = rows;
        this.columns = columns;
        this.products = new Product[rows][columns];
        this.maxItemsPerCell = maxItemsPerCell;
    }
    public void addProduct(Product product, int row, int column) throws IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException(String.format("Продукт для ячейки %d %d не указан (null).", row, column));
        }
        if (product.getAmount() > maxItemsPerCell) {
            throw new IllegalArgumentException(String.format("Количество товара в ячейке %d %d превышает %d единиц: %d",
                    row, column, maxItemsPerCell, product.getAmount()));
        }
        products[row - 1][column - 1] = product;
    }

    public void release(int row, int column) throws IllegalArgumentException {
        Product pr = products[row - 1][column - 1];
        if (pr == null) {
            throw new IllegalArgumentException(String.format("В ячейке %d %d нет товара.", row, column));
        }

        if (pr.getAmount() > 0) {
            pr.setAmount(pr.getAmount() - 1);
        } else {
            throw new IllegalArgumentException(String.format("В ячейке %d %d не осталось товара.", row, column));
        }
    }

    public void delProduct(int row, int column) {
        products[row - 1][column - 1] = null;
    }

    public Product[][] getProducts() {
        return products.clone();
    }
    public Product getProduct(int row, int column) {
        return products[row - 1][column - 1];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMaxItemsPerCell() {
        return maxItemsPerCell;
    }

}