package projava;

public class chap7_12 {
    public static void main(String[] args) {
        try(ResourceSample resource = new ResourceSample()) {
            System.out.println("try");
        } catch (Exception e) {
            System.out.println("catch:" + e.getMessage());
        } finally {
            System.out.println("finally");
        }
    }
}
class ResourceSample implements AutoCloseable {
    @Override public void close() throws Exception {
        System.out.println("close()");
        throw new Exception("Exception!");
    }
}

