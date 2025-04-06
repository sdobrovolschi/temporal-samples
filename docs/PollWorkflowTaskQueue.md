### [WorkflowService API PollWorkflowTaskQueue](https://github.com/temporalio/api/blob/master/temporal/api/workflowservice/v1/service.proto#L180)

#### Request
```protobuf
task_queue {
  name: "transfers"
  kind: TASK_QUEUE_KIND_NORMAL
}
identity: "18825@Stanislavs-MacBook-Pro.local"
worker_version_capabilities {
}
```

#### Response
```protobuf
task_token: "\n$32049b68-7872-4094-8e63-d0dd59896a83\022$411822aa-dcf7-4d16-8428-676a3ca68a28\032$01960b1a-60c6-71f6-b908-22f1f6b4337c \002(\001J\n\b\375\003\020\211\200\300\004\030\001P\003b\f\b\330\352\311\277\006\020\325\332\315\200\001"
workflow_execution {
  workflow_id: "411822aa-dcf7-4d16-8428-676a3ca68a28"
  run_id: "01960b1a-60c6-71f6-b908-22f1f6b4337c"
}
workflow_type {
  name: "transfer-initiation"
}
started_event_id: 3
attempt: 1
history {
  events {
    event_id: 1
    event_time {
      seconds: 1743943000
      nanos: 262129213
    }
    event_type: EVENT_TYPE_WORKFLOW_EXECUTION_STARTED
    task_id: 9437184
    workflow_execution_started_event_attributes {
      workflow_type {
        name: "transfer-initiation"
      }
      task_queue {
        name: "transfers"
        kind: TASK_QUEUE_KIND_NORMAL
      }
      input {
        payloads {
          metadata {
            key: "encoding"
            value: "json/plain"
          }
          data: "{\"fromAccountId\":\"ae4dbb91-2537-4d66-aa1c-831bcda82e36\",\"toAccountId\":\"87535500-2500-419b-bfb3-2e1e318c1aa9\",\"amount\":{\"value\":100,\"currency\":\"USD\"}}"
        }
      }
      workflow_execution_timeout {
      }
      workflow_run_timeout {
      }
      workflow_task_timeout {
        seconds: 5
      }
      original_execution_run_id: "01960b1a-60c6-71f6-b908-22f1f6b4337c"
      identity: "18825@Stanislavs-MacBook-Pro.local"
      first_execution_run_id: "01960b1a-60c6-71f6-b908-22f1f6b4337c"
      attempt: 1
      first_workflow_task_backoff {
      }
      header {
      }
      workflow_id: "411822aa-dcf7-4d16-8428-676a3ca68a28"
    }
  }
  events {
    event_id: 2
    event_time {
      seconds: 1743943000
      nanos: 262251004
    }
    event_type: EVENT_TYPE_WORKFLOW_TASK_SCHEDULED
    task_id: 9437185
    workflow_task_scheduled_event_attributes {
      task_queue {
        name: "transfers"
        kind: TASK_QUEUE_KIND_NORMAL
      }
      start_to_close_timeout {
        seconds: 5
      }
      attempt: 1
    }
  }
  events {
    event_id: 3
    event_time {
      seconds: 1743943000
      nanos: 269708629
    }
    event_type: EVENT_TYPE_WORKFLOW_TASK_STARTED
    task_id: 9437190
    workflow_task_started_event_attributes {
      scheduled_event_id: 2
      identity: "18825@Stanislavs-MacBook-Pro.local"
      request_id: "a2e08fb7-841c-4c01-b7bd-0e3edf92b7ff"
      history_size_bytes: 464
    }
  }
}
workflow_execution_task_queue {
  name: "transfers"
  kind: TASK_QUEUE_KIND_NORMAL
}
scheduled_time {
  seconds: 1743943000
  nanos: 262251004
}
started_time {
  seconds: 1743943000
  nanos: 269708629
}
```

### [MatchingService API PollWorkflowTaskQueue](https://github.com/temporalio/temporal/blob/main/proto/internal/temporal/server/api/matchingservice/v1/service.proto#L38)
