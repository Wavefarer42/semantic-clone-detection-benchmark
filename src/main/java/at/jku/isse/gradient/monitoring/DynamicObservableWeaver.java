package at.jku.isse.gradient.monitoring;

import at.jku.isse.gradient.runtime.__Gradient_Observable__;
import at.jku.isse.gradient.runtime.__TimeBased_Gradient_Observable__;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class DynamicObservableWeaver {
  @DeclareParents(
      value = "!is(InterfaceType) && !is(EnumType) && at.jku.isse.clones..*",
      defaultImpl = __TimeBased_Gradient_Observable__.class
  )
  private __Gradient_Observable__ implementedInterfaces;
}
