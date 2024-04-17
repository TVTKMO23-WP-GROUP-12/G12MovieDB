function sendToAnalytics(metric) {
	const body = JSON.stringify(metric);
	const url = 'http://localhost:8080'; //FIXME

	if (navigator.sendBeacon) {
		navigator.sendBeacon(url, body);
	} else {
		fetch(url, {body, method: 'POST', keepalive: true });
	}
}
reportWebVitals(console.log);


