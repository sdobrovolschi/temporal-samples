### [WorkflowService API RespondWorkflowTaskCompleted](https://github.com/temporalio/api/blob/master/temporal/api/workflowservice/v1/service.proto#L192)

#### Request
```protobuf
task_token: "\n$32049b68-7872-4094-8e63-d0dd59896a83\022$18826491-13f7-4860-88b4-cb2d2d302d44\032$01960bb7-055f-7cb9-b1be-d1944a082a97 \002(\001J\n\b\274\001\020\211\200\300\003\030\001P\003b\v\b\362\272\312\277\006\020\333\330\204\f"
commands {
  command_type: COMMAND_TYPE_SCHEDULE_ACTIVITY_TASK
  schedule_activity_task_command_attributes {
    activity_id: "f2d46480-21c0-3515-abc2-a3ef6b19c7a8"
    activity_type {
      name: "account-withdrawal"
    }
    task_queue {
      name: "transfers"
    }
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
    schedule_to_close_timeout {
    }
    schedule_to_start_timeout {
    }
    start_to_close_timeout {
      seconds: 2
    }
    heartbeat_timeout {
    }
    retry_policy {
      initial_interval {
        seconds: 5
      }
      backoff_coefficient: 1.0
      maximum_interval {
      }
    }
    use_workflow_build_id: true
  }
}
identity: "20447@Stanislavs-MacBook-Pro.local"
sticky_attributes {
  worker_task_queue {
    name: "20447@Stanislavs-MacBook-Pro.local:ca47685b-2288-4465-97b4-112f8241d619"
    kind: TASK_QUEUE_KIND_STICKY
    normal_name: "transfers"
  }
  schedule_to_start_timeout {
    seconds: 5
  }
}
namespace: "temporal-system"
worker_version_stamp {
}
sdk_metadata {
  lang_used_flags: 1
  sdk_name: "temporal-java"
  sdk_version: "1.28.4"
 }
metering_metadata {
}
capabilities {
  discard_speculative_workflow_task_with_events: true
}
```

#### Response
```protobuf
```

### [HistoryService API RespondWorkflowTaskCompleted](https://github.com/temporalio/temporal/blob/main/proto/internal/temporal/server/api/historyservice/v1/service.proto#L77)
