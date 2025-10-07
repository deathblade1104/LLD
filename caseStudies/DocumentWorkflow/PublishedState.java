package caseStudies.DocumentWorkflow;

public class PublishedState implements DocumentState {

  @Override
  public void next(Document document) {
    System.out.println("✅ Document is published!");
    System.out.println("📜 Content: " + document.getContent());

    if (document.canDocumentBeArchived()) {
      System.out.println("Archiving document...");
      document.setState(new ArchivedState());
    } else {
      System.out.println("Document stays Published.");
    }
  }

  @Override
  public String getStateName() {
    return DocumentStateEnum.PUBLISHED.name();
  }
}
