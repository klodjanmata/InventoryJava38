package Data;

import entities.Invoice;
import entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceData extends Invoice {

    private List<Sale> sales = new ArrayList<>();

    public void addSale(Sale sale){
        sales.add(sale);
    }

    public Invoice getInvoice(){
        Invoice invoice = new Invoice();
        invoice.setDate(getDate());
        invoice.setTotal(getTotal());
        return invoice;
    }

}
