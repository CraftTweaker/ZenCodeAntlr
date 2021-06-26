MCCommand(context => {
	context.getSource().sendFeedback("I was called!", true);
	return 0;
});
