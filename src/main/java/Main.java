import com.liwinli.basic.object.flow.init.ParentClass;
import com.liwinli.basic.object.flow.init.SubClass;
import com.liwinli.concurrency.ConcurrencyVSSerial;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("+++++++++++++++++ This is a java program ++++++++++++++++++++++");
//        LTFile.listPath("/Volumes/KINGSTON/image");
//        ParentClass p = new ParentClass();
        ConcurrencyVSSerial.test(10000);
        ConcurrencyVSSerial.test(100000);
        ConcurrencyVSSerial.test(1000000);
        ConcurrencyVSSerial.test(10000000);
        ConcurrencyVSSerial.test(100000000);
        System.out.println("---------------------------------------------------------------");
//        SubClass s = new SubClass();
    }
}
