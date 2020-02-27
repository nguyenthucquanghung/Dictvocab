package lomo.hust.dictvocab.fragment;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.activity.MarkWordActivity;
import lomo.hust.dictvocab.adapter.TagAdapter;
import lomo.hust.dictvocab.base.BaseFragment;
import lomo.hust.dictvocab.object.ResponseTags;
import lomo.hust.dictvocab.object.WordPost;
import lomo.hust.dictvocab.service.RetrofitService;
import lomo.hust.dictvocab.utils.Constant;
import lomo.hust.dictvocab.utils.SharedPreferencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VocabularyFragment extends BaseFragment {
    TextView tvAll;
    List<String> tags;
    RecyclerView rvTags;
    String allText ="";
    @Override
    protected void setupUI() {
//        TagAdapter adapter = new TagAdapter(getContext(), )
//        if (SharedPreferencesSingleton.getInstance().get(Constant.LOG_IN_STATE, Boolean.class)) {
//            tags = new ArrayList<>();
//            Retrofit retrofit = new Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl(Constant.API)
//                    .build();
//            RetrofitService service = retrofit.create(RetrofitService.class);
//            String token = "Bearer " + SharedPreferencesSingleton.getInstance().get(Constant.LOGIN_TOKEN, String.class);
//            Call<ResponseTags> call = service.getTags(token);
//            call.enqueue(new Callback<ResponseTags>() {
//                @Override
//                public void onResponse(Call<ResponseTags> call, Response<ResponseTags> response) {
//                    try {
//                        if (response.body().getMes() != null) {
//                        } else {
//                            tags.clear();
//                            tags.addAll(response.body().getTags());
//
//                        }
//                    } catch (NullPointerException e) {
//                        tags.clear();
//                        tags.addAll(response.body().getTags());
//
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<ResponseTags> call, Throwable t) {
//                }
//            });
//        }
    }

    @Override
    protected void findViewById(View view) {
        tvAll = view.findViewById(R.id.tv_all);
        rvTags = view.findViewById(R.id.rv_tags);
    }

    @Override
    protected int getLayoutInflaterId() {
        return R.layout.fragment_vocabulary;
    }
}
