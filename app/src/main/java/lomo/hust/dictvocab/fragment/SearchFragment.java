package lomo.hust.dictvocab.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.activity.WordDetailActivity;
import lomo.hust.dictvocab.adapter.WordAdapter;
import lomo.hust.dictvocab.base.BaseFragment;
import lomo.hust.dictvocab.listener.RecyclerViewItemClickListener;
import lomo.hust.dictvocab.object.Word;
import lomo.hust.dictvocab.service.RetrofitService;
import lomo.hust.dictvocab.utils.Constant;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class SearchFragment extends BaseFragment {
    private static final String TAG = "hehehe";
    private FloatingSearchView searchView;
    private List<Word> mWords;
    private RecyclerView rvWords;
    private WordAdapter mWordAdapter;
    private TextView tvAlt;
    private Call<List<Word>> wordsCallback;

    @Override
    protected void setupUI() {
        tvAlt.setVisibility(View.VISIBLE);
        wordsCallback = new Call<List<Word>>() {
            @Override
            public Response<List<Word>> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<List<Word>> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<List<Word>> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        setupRecyclerView();
        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
            }

            @Override
            public void onSearchAction(String currentQuery) {
                searchView.showProgress();
                getSearchedResults(currentQuery, true);
            }
        });
        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                searchView.showProgress();
                wordsCallback.cancel();
                if (newQuery.equals("")) {
                    mWords.clear();
                    mWordAdapter.updateData(mWords);
                    searchView.hideProgress();
                    tvAlt.setVisibility(View.VISIBLE);
                } else {
                    getSearchedResults(newQuery, false);
                }
            }
        });
    }

    private void getSearchedResults(String query, final boolean isSearchBehavior){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.API)
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        wordsCallback = service.getAutoComplete(query);
        wordsCallback.enqueue(new Callback<List<Word>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                tvAlt.setVisibility(View.INVISIBLE);
                mWords.clear();
                mWords = response.body();
                if (mWords.size() == 0 && isSearchBehavior) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getContext(),
                                    "Cannot find this word!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                searchView.hideProgress();
                mWordAdapter.updateData(mWords);
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                if (isSearchBehavior) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getContext(),
                                    "Server error!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    private void setupRecyclerView() {
        mWords = new ArrayList<>();
        mWordAdapter = new WordAdapter(getContext(), mWords);
        rvWords.setAdapter(mWordAdapter);
        rvWords.setLayoutManager(new LinearLayoutManager(getContext()));
        rvWords.addOnItemTouchListener(new RecyclerViewItemClickListener(getContext(), rvWords,
                new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WordDetailActivity.class);
                Word word = mWords.get(position);
                String keyAndType = word.getKey();
                if (!word.getType().equals(""))
                    keyAndType += " (" + word.getType() + ")";
                StringBuilder traits = new StringBuilder();
                for (String trait: word.getTrait())
                    traits.append(trait).append("\n");
                String traitList = traits.toString();
                StringBuilder meanings = new StringBuilder();
                for (String meaning: word.getMeaning())
                    meanings.append(" - ").append(meaning).append("\n");
                String meaningList = meanings.toString();

                intent.putExtra(Constant.KEY, keyAndType);
                intent.putExtra(Constant.TRAITS, traitList);
                intent.putExtra(Constant.MEANING, meaningList);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        }));
    }

    @Override
    protected void findViewById(View view) {
        searchView = view.findViewById(R.id.floating_search_view);
        rvWords = view.findViewById(R.id.rv_word_list);
        tvAlt = view.findViewById(R.id.tv_alt);
    }

    @Override
    protected int getLayoutInflaterId() {
        return R.layout.fragment_search;
    }
}
