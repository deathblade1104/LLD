package caseStudies.DocumentWorkflow;

public class DocumentWorkflowMain {
  public static void main(String[] args) {
    Document doc = new Document();
    System.out.println("Initial State: " + doc.getCurrentState());

    doc.next();
    System.out.println("State: " + doc.getCurrentState());

    doc.next();
    System.out.println("State: " + doc.getCurrentState());

    doc.next();
    System.out.println("State: " + doc.getCurrentState());

    doc.next();
    System.out.println("Final State: " + doc.getCurrentState());
  }
}
