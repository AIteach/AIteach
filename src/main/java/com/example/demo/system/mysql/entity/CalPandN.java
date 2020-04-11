package com.example.demo.system.mysql.entity;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;
import lombok.Data;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/*
 * 用于计算积极性值和消极性值
 */
@Data
@ToString
public class CalPandN {

	private JiebaSegmenter segmenter = new JiebaSegmenter();
	private static final String PROB_EMIT = "/text.txt";
	private Map<String, Double> emit = null;

	CalPandN() {
		Load();
	}

	public void Load() {
		// 加载词典
		InputStream is = this.getClass().getResourceAsStream(PROB_EMIT);
		try {
			emit = new HashMap<String, Double>();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			while (br.ready()) {
				String line = br.readLine();
				String[] tokens = line.split("\t");
				emit.put(tokens[0], Double.parseDouble(tokens[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(String.format(Locale.getDefault(), "text load failure"));
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (IOException e) {
				System.err.println(String.format(Locale.getDefault(), "%s: close failure!", PROB_EMIT));
			}
		}
		System.out.println(String.format(Locale.getDefault(), "text load finished!"));
	}

	public double[] getPositiveAndNagetive(String text) {
		//计算句子的词性，返回一个double数组 ，第一个值为积极的，第二个值为消极的
		List<SegToken> tokens = segmenter.process(text, SegMode.SEARCH);
		double[] num = new double[2];
		for (SegToken i : tokens) {
			System.out.println(i.word);
			if (emit.containsKey(i.word)) {
				double key = emit.get(i.word);
				if (key > 0)
					num[0] += key;
				else
					num[1] += key;
			}
		}
		return num; //
	}
}
