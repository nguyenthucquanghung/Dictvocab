package lomo.hust.dictvocab.fragment;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.activity.MarkWordActivity;
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
    String allText ="";
    @Override
    protected void setupUI() {
        final List<String> tags = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.API)
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        String token = "Bearer " + SharedPreferencesSingleton.getInstance().get(Constant.LOGIN_TOKEN, String.class);
        Call<ResponseTags> call = service.getTags(token);
        call.enqueue(new Callback<ResponseTags>() {
            @Override
            public void onResponse(Call<ResponseTags> call, Response<ResponseTags> response) {
                try {
                    if (response.body().getMes() != null) {
                    } else {
                        tags.addAll(response.body().getTags());
                        for (String tag: tags) {
                            allText = allText + tag;
                        }
                        tvAll.setText(allText);
                    }
                } catch (NullPointerException e) {
                    tags.addAll(response.body().getTags());
                    for (String tag: tags) {
                        allText = allText + tag;
                    }
                    tvAll.setText(allText);
                }

            }

            @Override
            public void onFailure(Call<ResponseTags> call, Throwable t) {
            }
        });
    }

    @Override
    protected void findViewById(View view) {
        tvAll = view.findViewById(R.id.tv_all);
    }

    @Override
    protected int getLayoutInflaterId() {
        return R.layout.fragment_vocabulary;
    }
}
