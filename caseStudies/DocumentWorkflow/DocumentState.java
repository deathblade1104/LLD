package caseStudies.DocumentWorkflow;

public interface DocumentState {
  void next(Document document);

  String getStateName();
}
