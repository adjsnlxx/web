package com.web.fsm;

import au.com.ds.ef.EasyFlow;
import au.com.ds.ef.EventEnum;
import au.com.ds.ef.FlowBuilder;
import au.com.ds.ef.StateEnum;
import au.com.ds.ef.StatefulContext;
import au.com.ds.ef.SyncExecutor;
import au.com.ds.ef.call.ContextHandler;
import au.com.ds.ef.err.LogicViolationError;

public class FSMTest {

	private EasyFlow<FlowContext> flow;
	public FlowContext context;

	public void start() {
		initFlow();
		bindFlow();

		context = new FlowContext();
		flow.start(context);
	}

	private void initFlow() {
		flow = FlowBuilder
				.from(MyState.STATE_SHOW)
				.transit(FlowBuilder
						.on(MyEvent.EVENT_WAIT)
						.to(MyState.STATE_WAIT)
						.transit(FlowBuilder
								.on(MyEvent.EVENT_STOP)
								.to(MyState.STATE_STOP)
								.transit(FlowBuilder.on(MyEvent.EVENT_SHOW).to(MyState.STATE_SHOW))));

		flow.executor(new SyncExecutor());
	}

	private void bindFlow() {
		flow.whenEnter(MyState.STATE_SHOW, new ContextHandler<StatefulContext>() {
			@Override
			public void call(StatefulContext context) throws Exception {
				System.out.println("state show");
			}
		});

		flow.whenEvent(MyEvent.EVENT_WAIT, new ContextHandler<StatefulContext>() {
			@Override
			public void call(StatefulContext context) throws Exception {
				System.out.println("state wait");
			}
		});

		flow.whenEvent(MyEvent.EVENT_STOP, new ContextHandler<StatefulContext>() {
			@Override
			public void call(StatefulContext context) throws Exception {
				System.out.println("state stop");
			}
		});
	}

	public EasyFlow<FlowContext> getFlow() {
		return flow;
	}

	public void setFlow(EasyFlow<FlowContext> flow) {
		this.flow = flow;
	}

	public FlowContext getContext() {
		return context;
	}

	public void setContext(FlowContext context) {
		this.context = context;
	}

	static class FlowContext extends StatefulContext {

	}

	static enum MyState implements StateEnum {
		STATE_SHOW, STATE_WAIT, STATE_STOP
	}

	static enum MyEvent implements EventEnum {
		EVENT_SHOW, EVENT_WAIT, EVENT_STOP
	}

	public static void main(String[] args) {
		FSMTest test = new FSMTest();
		test.start();

		try {
			test.getFlow().trigger(MyEvent.EVENT_WAIT, test.getContext());
			test.getFlow().trigger(MyEvent.EVENT_STOP, test.getContext());
		} catch (LogicViolationError logicViolationError) {
			logicViolationError.printStackTrace();
		}
	}

}
