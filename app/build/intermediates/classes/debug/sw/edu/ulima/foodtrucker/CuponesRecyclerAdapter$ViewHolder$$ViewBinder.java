// Generated code from Butter Knife. Do not modify!
package sw.edu.ulima.foodtrucker;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CuponesRecyclerAdapter$ViewHolder$$ViewBinder<T extends sw.edu.ulima.foodtrucker.CuponesRecyclerAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558620, "field 'image'");
    target.image = finder.castView(view, 2131558620, "field 'image'");
    view = finder.findRequiredView(source, 2131558621, "field 'title'");
    target.title = finder.castView(view, 2131558621, "field 'title'");
    view = finder.findRequiredView(source, 2131558622, "field 'desc'");
    target.desc = finder.castView(view, 2131558622, "field 'desc'");
  }

  @Override public void unbind(T target) {
    target.image = null;
    target.title = null;
    target.desc = null;
  }
}
