package com.duitang.service.karma.base;

import com.duitang.service.karma.stats.InstanceTag;

import org.HdrHistogram.Histogram;
import org.LatencyUtils.LatencyStats;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

/*
    TODO
    - abstract general metric unit, useful for metrics needs custom tags
 */
public class MetricUnit {

  private ByteBuffer targetBuffer;

  public String name;
  protected LatencyStats stats;
  protected Histogram histo;

  public MetricUnit(String name) {
    this.name = name;

    stats = new LatencyStats();
    histo = stats.getIntervalHistogram();
  }

  synchronized public Map<String, Object> sample() {
    Map<String, Object> ret = new HashMap<>();
    stats.getIntervalHistogramInto(histo);
    ret.put("timestamp", System.currentTimeMillis());
    ret.put("name", name);

    {
      InstanceTag loc = MetricCenter.getInstanceTag();
      String app = loc.app;
      if (app != null && !app.isEmpty()) {
        ret.put("app", app);
      }

      String host = loc.host;
      if (host != null && !host.isEmpty()) {
        ret.put("host", host);
      }

      long pid = loc.pid;
      if (pid > 0) {
        ret.put("pid", pid);
      }
    }

    ret.put("from", histo.getStartTimeStamp());
    ret.put("to", histo.getEndTimeStamp());

    long count = histo.getTotalCount();
    ret.put("count", count);
    ret.put("mean", histo.getMean());
    ret.put("max", count == 0 ? 0 : histo.getMaxValue());
    ret.put("min", count == 0 ? 0 : histo.getMinValue());
    ret.put("stddev", histo.getStdDeviation());

    long gap = histo.getEndTimeStamp() - histo.getStartTimeStamp();
    if (gap != 0) {
      ret.put("qps", count / (gap / 1000D));
    }

    ret.put("p50", histo.getValueAtPercentile(50D));
    ret.put("p75", histo.getValueAtPercentile(75D));
    ret.put("p80", histo.getValueAtPercentile(80D));
    ret.put("p85", histo.getValueAtPercentile(85D));
    ret.put("p90", histo.getValueAtPercentile(90D));
    ret.put("p95", histo.getValueAtPercentile(95D));
    ret.put("p98", histo.getValueAtPercentile(98D));
    ret.put("p99", histo.getValueAtPercentile(99D));
    ret.put("p999", histo.getValueAtPercentile(99.9D));
    ret.put("p9999", histo.getValueAtPercentile(99.99D));

    ret.put("histogram_b64", encodeCompressedArray(histo));
    return ret;
  }

  protected String encodeCompressedArray(final Histogram histogram) {
    if (targetBuffer == null || targetBuffer.capacity() < histogram.getNeededByteBufferCapacity()) {
      targetBuffer = ByteBuffer.allocate(histogram.getNeededByteBufferCapacity());
    }
    targetBuffer.clear();
    int compressedLength = histogram.encodeIntoCompressedByteBuffer(targetBuffer);
    byte[] compressedArray = Arrays.copyOf(targetBuffer.array(), compressedLength);
    return DatatypeConverter.printBase64Binary(compressedArray);
  }

  public void record(long latency) {
    stats.recordLatency(latency);
  }
}