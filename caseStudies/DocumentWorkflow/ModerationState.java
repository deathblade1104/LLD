package caseStudies.DocumentWorkflow;

public class ModerationState implements DocumentState {

  @Override
  public void next(Document document) {
    System.out.println("\uD83D\uDC40 Document is under moderation...");

    if (document.getApproval()) {
      System.out.println("Document approved ✅. Moving to Published state...");
      document.setState(new PublishedState());
    } else {
      System.out.println("Document rejected ❌. Returning to Draft...");
      document.resetDraft();
      document.setState(new DraftState());
    }
  }

  @Override
  public String getStateName() {
    return DocumentStateEnum.MODERATION.name();
  }
}
