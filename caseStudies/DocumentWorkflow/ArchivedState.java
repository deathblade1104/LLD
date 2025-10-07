package caseStudies.DocumentWorkflow;

public class ArchivedState implements DocumentState {

  @Override
  public void next(Document document) {
    System.out.println("\uD83D\uDCE6 Document is archived. No further actions possible.");
  }

  @Override
  public String getStateName() {
    return DocumentStateEnum.ARCHIVED.name();
  }
}
