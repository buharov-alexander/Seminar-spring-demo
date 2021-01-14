package com.example.demo.controllers;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
@RequestMapping("/content")
public class StreamController {

	private ExecutorService nonBlockingService = Executors.newCachedThreadPool();

	@GetMapping(value = "/stream")
	public ResponseEntity<StreamingResponseBody> streamData() {
		StreamingResponseBody responseBody = response -> {
			for (int i = 1; i <= 10; i++) {
				try {
					Thread.sleep(1000);
					response.write(("{\"Response part:\"" + i + "}\n").getBytes());
					response.flush();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		return ResponseEntity.ok()
				.body(responseBody);
	}

	@GetMapping(value = "/sse")
	public SseEmitter sseData() {
		SseEmitter emitter = new SseEmitter();
		nonBlockingService.execute(() -> {
			try {
				for (int i = 1; i <= 10; i++) {
					Thread.sleep(1000);
					emitter.send("SSE event: " + new Date());
				}
				emitter.complete();
			} catch (Exception ex) {
				emitter.completeWithError(ex);
			}
		});
		return emitter;
	}
}
