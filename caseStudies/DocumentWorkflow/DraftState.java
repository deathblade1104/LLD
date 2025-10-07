package caseStudies.DocumentWorkflow;

public class DraftState implements DocumentState {

  @Override
  public void next(Document document) {
    System.out.println("\uD83D\uDCC4 Currently in Draft State...");

    document.upsertContent();

    if (!document.isDraftDone()) {
      System.out.println("Draft not completed. Staying in Draft state.");
      return;
    }

    System.out.println("Draft completed. Moving to Moderation...");
    document.setState(new ModerationState());
  }

  @Override
  public String getStateName() {
    return DocumentStateEnum.DRAFT.name();
  }
}
