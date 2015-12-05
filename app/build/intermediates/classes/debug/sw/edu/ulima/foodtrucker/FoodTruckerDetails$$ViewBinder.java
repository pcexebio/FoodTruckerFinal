// Generated code from Butter Knife. Do not modify!
package sw.edu.ulima.foodtrucker;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FoodTruckerDetails$$ViewBinder<T extends sw.edu.ulima.foodtrucker.FoodTruckerDetails> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558560, "field 'nombre'");
    target.nombre = finder.castView(view, 2131558560, "field 'nombre'");
    view = finder.findRequiredView(source, 2131558562, "field 'dir'");
    target.dir = finder.castView(view, 2131558562, "field 'dir'");
    view = finder.findRequiredView(source, 2131558564, "field 'horario'");
    target.horario = finder.castView(view, 2131558564, "field 'horario'");
    view = finder.findRequiredView(source, 2131558559, "field 'titulo'");
    target.titulo = finder.castView(view, 2131558559, "field 'titulo'");
    view = finder.findRequiredView(source, 2131558513, "field 'img'");
    target.img = finder.castView(view, 2131558513, "field 'img'");
  }

  @Override public void unbind(T target) {
    target.nombre = null;
    target.dir = null;
    target.horario = null;
    target.titulo = null;
    target.img = null;
  }
}
