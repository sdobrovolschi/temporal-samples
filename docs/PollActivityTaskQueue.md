### [WorkflowService API PollWorkflowTaskQueue](https://github.com/temporalio/api/blob/master/temporal/api/workflowservice/v1/service.proto#L225)

#### Request
```protobuf
namespace: "temporal-system"
task_queue {
  name: "transfers"
}
identity: "18825@Stanislavs-MacBook-Pro.local"
task_queue_metadata {
  max_tasks_per_second {
    value: 800.0
  }
}
worker_version_capabilities {
}
```

#### Response
```protobuf
task_token: "\n$32049b68-7872-4094-8e63-d0dd59896a83\022$18826491-13f7-4860-88b4-cb2d2d302d44\032$01960bb7-055f-7cb9-b1be-d1944a082a97 \005(\0012$f2d46480-21c0-3515-abc2-a3ef6b19c7a8B\022account-withdrawalJ\n\b\274\001\020\220\200\300\003\030\001"
workflow_namespace: "temporal-system"
workflow_type {
  name: "transfer-initiation"
}
workflow_execution {
  workflow_id: "18826491-13f7-4860-88b4-cb2d2d302d44"
  run_id: "01960bb7-055f-7cb9-b1be-d1944a082a97"
}
activity_type {
  name: "account-withdrawal"
}
activity_id: "f2d46480-21c0-3515-abc2-a3ef6b19c7a8"
header {
}
input {
  payloads {
    metadata {
      key: "encoding"
      value: "json/plain"
    }
    data: "{\"accountId\":\"7f51a5d8-86cb-454d-81e8-7b9c12b85813\",\"amount\":{\"value\":100,\"currency\":\"USD\"}}"
  }
}
scheduled_time {
  seconds: 1743953266
  nanos: 182853715
}
current_attempt_scheduled_time {
  seconds: 1743953266
  nanos: 182853715
}
started_time {
  seconds: 1743953266
  nanos: 188522215
}
attempt: 1
schedule_to_close_timeout {
}
start_to_close_timeout {
  seconds: 2
}
heartbeat_timeout {
}
```

### [MatchingService API PollWorkflowTaskQueue](https://github.com/temporalio/temporal/blob/main/proto/internal/temporal/server/api/matchingservice/v1/service.proto#L43)
