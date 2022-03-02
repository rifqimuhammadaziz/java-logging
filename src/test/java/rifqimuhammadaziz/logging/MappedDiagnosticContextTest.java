package rifqimuhammadaziz.logging;

import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import java.util.UUID;

public class MappedDiagnosticContextTest {

    // without MappedDiagnosticContext (MDC)
//    @Test
//    void testRequestId() {
//        String requestId = UUID.randomUUID().toString();
//
//        MyController controller = new MyController(new MyService(new MyRepository()));
//
//        controller.save(requestId);
//    }

    // with MDC and same thread (requestId can be accessed only in same thread)
    @Test
    void testRequestIdWithMDC() {
        String requestId = UUID.randomUUID().toString();

        MyController controller = new MyController(new MyService(new MyRepository()));

        MDC.put("requestId", requestId);
        controller.save();
        MDC.remove("requestId");
    }

    // with MDC and different thread, requestId cannot be accessed
    @Test
    void testRequestIdWithMDCOtherThread() {
        String requestId = UUID.randomUUID().toString();

        MyController controller = new MyController(new MyService(new MyRepository()));

        MDC.put("requestId", requestId);
        new Thread(() -> {
            controller.save();
        }).start();
        MDC.remove("requestId");
    }

    // with MDC and support multi thread (requestId can be accessed only in same thread)
    @Test
    void testMultiThreadMDC() throws InterruptedException {
        MyController controller = new MyController(new MyService(new MyRepository()));

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String requestId = UUID.randomUUID().toString();
                MDC.put("requestId", requestId);
                controller.save();
                MDC.remove("requestId");
            }).start();
        }

        Thread.sleep(1000L);
    }
}
