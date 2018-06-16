package fr.labs.zwing.randomuserapp.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import de.hdodenhof.circleimageview.CircleImageView;
import fr.labs.zwing.randomuserapp.R;
import fr.labs.zwing.randomuserapp.database.entity.User;
import fr.labs.zwing.randomuserapp.repositories.UserRepository;
import fr.labs.zwing.randomuserapp.view.bottomsheet.BottomSheetDialog;
import fr.labs.zwing.randomuserapp.viewmodel.UserCollectionViewModel;

public class UserCollectionFragment extends Fragment {


    private List<User> listOfUsers;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private UserCollectionViewModel viewModel;


    private LayoutInflater layoutInflater;
    private CustomAdapter adapter;

    @BindView(R.id.rec_list_activity)
    RecyclerView recyclerView;
    @BindView(R.id.tlb_list_activity)
    Toolbar toolbar;
    @BindView(R.id.tv_network_issue)
    TextView tvNetworkIssue;


    public UserCollectionFragment() { }

    public static UserCollectionFragment newInstance() {
        return new UserCollectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usercollection_fragment, container, false);
        ButterKnife.bind(this, view);

        layoutInflater = getActivity().getLayoutInflater();
        toolbar.setTitle(R.string.title_list_activity);
        toolbar.setTitleMarginStart(72);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }



    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel(){
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserCollectionViewModel.class);
        viewModel.init();

        final Observer<String> connectivityObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String connectivityState) {
                if(connectivityState.equals(UserRepository.RESPONSE_RECEIVED))
                    tvNetworkIssue.setVisibility(View.GONE);
                else
                    tvNetworkIssue.setVisibility(View.VISIBLE);
            }
        };

        viewModel.getListOfUsers().observe(this, this::setListData);
        viewModel.getRequestState().observe(this, connectivityObserver);
    }


    public void setListData(List<User> listOfData) {
        this.listOfUsers = listOfData;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());


        recyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

    }


    /*-------------------- RecyclerView ----------------------*/

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {//6

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.usercollection_item, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {

            User currentUser = listOfUsers.get(position);

            Picasso.with(holder.imageView.getContext())
                    .load(currentUser.getPicture().getMedium())
                    .centerCrop().fit()
                    .into(holder.imageView);

            holder.tvName.setText(
                    buildStringUserName(currentUser)
            );

            holder.tvEmail.setText(
                    currentUser.getEmail()
            );

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

        @Override
        public int getItemCount() {

            return listOfUsers.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            @BindView(R.id.imv_circle)
            CircleImageView imageView;
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_email)
            TextView tvEmail;
            @BindView(R.id.root_list_user)
            ViewGroup container;

            public CustomViewHolder(View itemView) {
                super(itemView);

                ButterKnife.bind(this, itemView);

                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

                User user = listOfUsers.get(
                        this.getAdapterPosition()
                );

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
                bottomSheetDialog.setUser(user);
                bottomSheetDialog.show(getFragmentManager(), bottomSheetDialog.getTag());

            }
        }

    }


}
