package compare;

import com.google.common.base.Equivalence;
import com.google.common.base.Objects;

public class NameEquivalence extends Equivalence<Persion> {

    @Override
    protected boolean doEquivalent(Persion a, Persion b) {
        if (null == a && null == b) {
            return true;
        }
        if (null == a || null == b) {
            return false;
        }
        return Objects.equal(a.getName(), b.getName());
    }

    @Override
    protected int doHash(Persion persion) {
        return Objects.hashCode(persion);
    }

    public static void main(String[] args) {
        Persion a = new Persion();
        a.setName("Wang");
        Persion b = new Persion();
        b.setName("Wang");
        NameEquivalence nameEqu = new NameEquivalence();
        System.out.println(nameEqu.wrap(a).equals(nameEqu.wrap(b)));
    }
}
