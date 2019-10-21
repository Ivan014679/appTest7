package com.example.marketapp.classes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marketapp.R;
import com.example.marketapp.database.MarketDB;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private Context mContext;

    public ProductAdapter(List<Product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_card_view, viewGroup, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        productViewHolder.tvName.setText(productList.get(i).getName());
        productViewHolder.tvCategory.setText(productList.get(i).getCategory());
        productViewHolder.amount = productList.get(i).getQuantity();
    }

    @Override
    public int getItemCount() {
        try {
            return productList.size();
        }catch(java.lang.NullPointerException ex){
            return 0;
        }
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvName, tvCategory;
        int amount;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvProduct);
            tvCategory = itemView.findViewById(R.id.tvCategory);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
