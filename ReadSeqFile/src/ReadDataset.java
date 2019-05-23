import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;

import com.google.protobuf.CodedInputStream;

import edu.iastate.cs.boa.BoaClient;

public class ReadDataset {

	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fileSystem = FileSystem.get(conf);
		
		String astpath = "/Users/sumon/Research/PyDatagen/PythonDataset/final_updated2/ast/data";
		Writable key = new LongWritable();
		BytesWritable val = new BytesWritable();
		SequenceFile.Reader r = new SequenceFile.Reader(fileSystem, new Path(astpath), conf);
		//System.setOut(new PrintStream(new File("out.txt")));
		while (r.next(key, val)) {
			System.out.println("-- next project -- ");
			byte[] bytes = val.getBytes();
			CodedInputStream a = CodedInputStream.newInstance(bytes, 0, val.getLength());
			//boa.types.Ast.ASTRoot ast = boa.types.Ast.ASTRoot.parseFrom((CodedInputStream.newInstance(bytes, 0, val.getLength())));
			System.out.println(a);
			break;
		}
		r.close();
	}

}
