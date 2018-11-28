import java.util.ArrayList;
import java.util.List;

public class ResidueSeq extends Consensus {
	
	private String residueSequence;
	private int dataSize;

	public ResidueSeq(int[][] m, int len, SequenceData d) {
		super(len);
		this.matrix = m;
		this.residueSequence = "";
		this.dataSize = d.getNumberOfSequences();
		this.buildSequence();
	}
	
	private void buildSequence() {
		List<Integer> noneZeroList;
		for(int i = 0; i < this.length;i++) {
			
			// check if a motif is always found at current position
			// when three motifs are 0
			if(this.matrix[A_ROW][i] == this.dataSize) this.residueSequence += "A ";
			else if(this.matrix[T_ROW][i] == this.dataSize) this.residueSequence += "T ";
			else if(this.matrix[C_ROW][i] == this.dataSize) this.residueSequence += "C ";
			else if(this.matrix[G_ROW][i] == this.dataSize) this.residueSequence += "G ";
			
			noneZeroList = new ArrayList<Integer>();
			for(int y = 0; y < MAX_ROW; y++) {
				if(this.matrix[y][i] != 0) noneZeroList.add(y);
			}
			//System.out.println("Size:" +noneZeroList.size());
			
			//when two motifs are 0
			if(noneZeroList.size() == 2) {
				this.residueSequence += "[";
				for(Integer item : noneZeroList) {
					if(item == 0) this.residueSequence += "A";
					if(item == 1) this.residueSequence += "T";
					if(item == 2) this.residueSequence += "C";
					if(item == 3) this.residueSequence += "G";	
				}
				this.residueSequence += "] ";
			}
			
			//when only one motif is 0
			else if(noneZeroList.size() == 3) {
				this.residueSequence += "{";
				if(!noneZeroList.contains(0)) this.residueSequence += "A";
				else if(!noneZeroList.contains(1)) this.residueSequence += "T";
				else if(!noneZeroList.contains(2)) this.residueSequence += "C";
				else if(!noneZeroList.contains(3)) this.residueSequence += "G";
				this.residueSequence += "} ";
			}
			
			else if(noneZeroList.size() == 4) {
				this.residueSequence += "N ";
			}
			//this.residueSequence += " | ";
		}
	}
	
	public void printResidueSequence() {
		System.out.println("Residue Sequence:");
		System.out.println(this.residueSequence);
		System.out.println("Notations: \n"
				+ "A means A always found in that position \n"
				+ "[AT] means either A or T \n"
				+ "{A} any base except A \n"
				+ "N for any bases");
	}
}
