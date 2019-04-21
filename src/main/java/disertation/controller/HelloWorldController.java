package disertation.controller;

import disertation.utils.Analyzer;
import disertation.utils.Constants;
import disertation.utils.Prediction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.concurrent.CompletableFuture;

@RestController
class HelloController {

    @RequestMapping(value = "/predict", method = RequestMethod.POST, produces = {"application/json"})
    @Async
    public CompletableFuture<PredictionJSON> predict(@RequestParam("SYM") String ids) {
        System.err.println(ids);
        System.err.println(HtmlUtils.htmlUnescape(ids));
        return CompletableFuture.completedFuture(Prediction.predict(Constants.STOCK_DATA.get(HtmlUtils.htmlUnescape(ids))));
    }

    @RequestMapping(value = "/sma", method = RequestMethod.POST, produces = {"application/json"})
    @Async
    public CompletableFuture<PredictionJSON> sma(@RequestParam("SYM") String ids, @RequestParam("period") int windowsSize) {
        return CompletableFuture.completedFuture(Analyzer.sma(Constants.STOCK_DATA.get(HtmlUtils.htmlUnescape(ids)), windowsSize));
    }
}