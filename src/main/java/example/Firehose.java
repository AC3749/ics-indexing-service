

///Send records to Firehose

public PutRecordBatchResult putRecordBatch(List<Record> recordList) {
  PutRecordBatchRequest putRecordBatchRequest = new PutRecordBatchRequest();
  putRecordBatchRequest.setDeliveryStreamName(deliveryStreamName);
  putRecordBatchRequest.setRecords(recordList);
  // Put Record Batch records. Max No.Of Records we can put in a
  // single put record batch request is 500 and total size < 4MB
  PutRecordBatchResult putRecordBatchResult = null; 
  try {
     putRecordBatchResult = firehoseClient.putRecordBatch(putRecordBatchRequest);
  }catch(AmazonKinesisFirehoseException akfe){
     System.out.println("Amazon Kinesis Firehose Exception:" + akfe.getLocalizedMessage());
  }catch(Exception e){
     System.out.println("Connector Exception" + e.getLocalizedMessage());
  }
  return putRecordBatchResult;
  recordList.clear();
}