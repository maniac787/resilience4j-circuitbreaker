import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    // A number specifying the number of VUs to run concurrently.
    vus: 1000,
    // A string specifying the total duration of the test run.
    duration: '30s',
};

export default function() {
    const params = {
        headers: {
            'channel': 'BLAND',
            'customerId': '9002'
        },
    };

    http.get('https://eobqb116th.execute-api.us-east-1.amazonaws.com/Default/accounts?country=TDN', params);
    sleep(1);
}
