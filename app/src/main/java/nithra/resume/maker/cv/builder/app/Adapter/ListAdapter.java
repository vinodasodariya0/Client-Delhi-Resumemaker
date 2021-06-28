package nithra.resume.maker.cv.builder.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import nithra.resume.maker.cv.builder.app.R;

public class ListAdapter extends ArrayAdapter {
    Context context;
    LayoutInflater inflater;
    List<String> names;
    TextView pdfName;

    public ListAdapter(Context context2, int i, List<String> list) {
        super(context2, i, list);
        this.context = context2;
        this.names = list;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = this.inflater.inflate(R.layout.obj_list_item, (ViewGroup) null);
        this.pdfName = (TextView) inflate.findViewById(R.id.learn_cat_text);
        this.pdfName.setText(this.names.get(i));
        return inflate;
    }
}
