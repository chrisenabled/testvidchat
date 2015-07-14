package com.f80prototype.verizon.protoandroid.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.f80prototype.verizon.protoandroid.ConversationDataStructure;
import com.f80prototype.verizon.protoandroid.MainActivity;
import com.f80prototype.verizon.protoandroid.R;

import java.util.ArrayList;

/**
 * Created by inyanja on 7/13/15.
 */
@SuppressLint("ValidFragment")
public class ConversationFragment extends Fragment{
    private Button mConversationBtn;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ConversationDataStructure MyData;
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.message_rv);
        LinearLayoutManager llm;
        llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        MyData = new ConversationDataStructure();
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(MyData.myDataset());
        mRecyclerView.setAdapter(mAdapter);

        mConversationBtn = (Button) view.findViewById(R.id.my_conversation_btn);
        mConversationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(View.GONE);
                //VideoPagerFragment.upToDown();
                //MainActivity.validateScreen();
            }
        });

        return view;
    }

    public ConversationFragment(){}

    public void setUp(Context context){
        mContext = context;
    }


    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<String> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;

            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.grp_msg);
            }
        }

        public MyAdapter(ArrayList<String> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_conversation, parent, false);
            // set the view's size, margins, paddings and layout parameter
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            holder.mTextView.setText(mDataset.get(position));

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

}
