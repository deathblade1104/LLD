package caseStudies.DocumentWorkflow;

public class Document {
  private DocumentState state;
  private boolean isDocumentApproved;
  private boolean isDraftDone;
  private boolean isDocArchived;
  private String content;

  public Document() {
    this.state = new DraftState();
  }

  public void setState(DocumentState newState) {
    this.state = newState;
  }

  public void next() {
    this.state.next(this);
  }

  public String getCurrentState() {
    return this.state.getStateName();
  }

  public boolean getApproval() {
    return this.isDocumentApproved = true;
  }

  public boolean isDraftDone() {
    return this.isDraftDone = true;
  }

  public void upsertContent() {
    this.content = "This is the final version of the document.";
  }

  public String getContent() {
    return this.content;
  }

  public boolean canDocumentBeArchived() {
    return this.isDocArchived = true;
  }

  public void resetDraft() {
    this.isDraftDone = false;
    this.content = null;
  }
}
