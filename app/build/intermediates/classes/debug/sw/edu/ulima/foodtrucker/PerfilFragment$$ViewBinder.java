// Generated code from Butter Knife. Do not modify!
package sw.edu.ulima.foodtrucker;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PerfilFragment$$ViewBinder<T extends sw.edu.ulima.foodtrucker.PerfilFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558616, "field 'butMapa'");
    target.butMapa = finder.castView(view, 2131558616, "field 'butMapa'");
    view = finder.findRequiredView(source, 2131558614, "field 'tviUsuario'");
    target.tviUsuario = finder.castView(view, 2131558614, "field 'tviUsuario'");
    view = finder.findRequiredView(source, 2131558615, "field 'img'");
    target.img = finder.castView(view, 2131558615, "field 'img'");
  }

  @Override public void unbind(T target) {
    target.butMapa = null;
    target.tviUsuario = null;
    target.img = null;
  }
}
