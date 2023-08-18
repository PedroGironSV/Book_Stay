const report = require("multiple-cucumber-html-reporter");
const json = require("./test/report/cucumber_report.json");

function formatDate(){
    const date = new Date(json[0].elements[0].start_timestamp);
    let localDate = date.toDateString();
    let localTime = date.toLocaleTimeString();
    return localDate + ' ' + localTime;
}

function setUpReport() {

    let startTime = formatDate();

    report.generate({
        jsonDir: "./Execution_Report/",
        reportPath: "./Execution_Report/",
        openReportInBrowser: true,
        pageTitle: "Tests Report",
        reportName: "Portfolio Web Report",
        hideMetadata: true,
        displayDuration: true,
        metadata: {
            browser: {
                name: "chrome",
                version: "115",
            },
            device: "Local test machine",
            platform: {
                name: "Windows 10",
                version: "16.04",
            },
        },
        customData: {
            title: "Run info",
            data: [
                { label: "Project", value: "Book a stay" },
                { label: "Release", value: "1.2.0" },
                { label: "Execution Start Time", value: startTime }
            ],
        },
    });
}

setUpReport();