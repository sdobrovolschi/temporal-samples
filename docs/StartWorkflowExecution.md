### [WorkflowService API StartWorkflowExecution](https://github.com/temporalio/api/blob/master/temporal/api/workflowservice/v1/service.proto#L117)

#### Request
```protobuf
namespace: "temporal-system"
workflow_id: "f9e76967-0533-4f98-9586-cc5cdbae319a"
workflow_type {
name: "transfer-initiation"
}
task_queue {
name: "transfers"
}
input {
payloads {
metadata {
key: "encoding"
value: "json/plain"
}
data: "{\"fromAccountId\":\"0ac6ee00-e4b6-466e-adef-203d770d06a9\",\"toAccountId\":\"5a5978df-9e23-4c23-a889-0301d8b9952b\",\"amount\":{\"value\":100,\"currency\":\"USD\"}}"
}
}
workflow_execution_timeout {
}
workflow_run_timeout {
}
workflow_task_timeout {
seconds: 5
}
identity: "18408@Stanislavs-MacBook-Pro.local"
request_id: "16b6e237-b341-4fcf-b7b3-e4151b0f0634"
workflow_id_reuse_policy: WORKFLOW_ID_REUSE_POLICY_REJECT_DUPLICATE
header {
}
workflow_id_conflict_policy: WORKFLOW_ID_CONFLICT_POLICY_FAIL
```

#### Response
```protobuf
run_id: "01960b1a-60c6-71f6-b908-22f1f6b4337c"
started: true
```

### [HistoryService API StartWorkflowExecution](https://github.com/temporalio/temporal/blob/main/proto/internal/temporal/server/api/historyservice/v1/service.proto#L38)