package fr.labs.zwing.randomuserapp.view.bottomsheet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import fr.labs.zwing.randomuserapp.R;
import fr.labs.zwing.randomuserapp.database.entity.User;

/**
 * Created by ludov on 14,juin,2018
 */
public class BottomSheetDialog extends BottomSheetDialogFragment {

    private User user;
    private List<Attribute> attributes;

    private BottomSheetDialog.CustomAdapter adapter;
    private LayoutInflater layoutInflater;

    @BindView((R.id.imv_detail_circle))
    CircleImageView picture;
    @BindView(R.id.tv_detail_name)
    TextView tvName;
    @BindView(R.id.rec_attributes)
    RecyclerView recyclerView;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);


        View v = View.inflate(getContext(), R.layout.bottom_sheet_layout, null);
        ButterKnife.bind(this, v);
        layoutInflater = getActivity().getLayoutInflater();
        setListAttr();
        dialog.setContentView(v);


    }

    public void setUser(User user){
        this.user = user;
    }

    private void setListAttr() {

        Picasso.with(picture.getContext())
                .load(user.getPicture().getMedium())
                .centerCrop().fit()
                .into(picture);

        tvName.setText(
                buildStringUserName(user)
        );

        attributes = new ArrayList<>();
        attributes.add(new Attribute(R.drawable.ic_mail, user.getEmail()));
        attributes.add(new Attribute(R.drawable.ic_home, user.getLocation().toString()));
        attributes.add(new Attribute(R.drawable.ic_phone_iphone, user.getCell()));
        attributes.add(new Attribute(R.drawable.ic_phone, user.getPhone()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        adapter = new BottomSheetDialog.CustomAdapter();
        recyclerView.setAdapter(adapter);

    }

    private Spanned buildStringUserName(User currentUser){
        StringBuffer buffer = new StringBuffer();
        buffer.append("<b>");
        buffer.append(currentUser.getName().getFirst().substring(0,1).toUpperCase());
        buffer.append(currentUser.getName().getFirst().substring(1));
        buffer.append("</b> ");
        buffer.append(currentUser.getName().getLast().toUpperCase());
        return Html.fromHtml(buffer.toString());
    }

    /*-------------------- RecyclerView ----------------------*/

    public class CustomAdapter extends RecyclerView.Adapter<BottomSheetDialog.CustomAdapter.CustomViewHolder> {//6

        @Override
        public BottomSheetDialog.CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.bottom_sheet_item, parent, false);
            return new BottomSheetDialog.CustomAdapter.CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(BottomSheetDialog.CustomAdapter.CustomViewHolder holder, int position) {
            Attribute attribute = attributes.get(position);

            holder.imageView.setImageResource(
                    attribute.getDrawable()
            );
            holder.tvAttr.setText(
                    attribute.getText()
            );

        }

        @Override
        public int getItemCount() {
            return attributes.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.imv_attr)
            ImageView imageView;
            @BindView(R.id.tv_attr)
            TextView tvAttr;

            @BindView(R.id.root_item_attributes)
            ViewGroup container;


            public CustomViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

            }

        }

    }

    private class Attribute{

        private int drawable;
        private String text;

        public Attribute(int drawable, String text) {
            this.drawable = drawable;
            this.text = text;
        }

        public int getDrawable() {
            return drawable;
        }

        public void setDrawable(int drawable) {
            this.drawable = drawable;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
