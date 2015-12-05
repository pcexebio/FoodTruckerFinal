// Generated code from Butter Knife. Do not modify!
package sw.edu.ulima.foodtrucker;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InicioActivity$$ViewBinder<T extends sw.edu.ulima.foodtrucker.InicioActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558617, "field 'img'");
    target.img = finder.castView(view, 2131558617, "field 'img'");
    view = finder.findRequiredView(source, 2131558619, "field 'email'");
    target.email = finder.castView(view, 2131558619, "field 'email'");
    view = finder.findRequiredView(source, 2131558618, "field 'user'");
    target.user = finder.castView(view, 2131558618, "field 'user'");
  }

  @Override public void unbind(T target) {
    target.img = null;
    target.email = null;
    target.user = null;
  }
}
