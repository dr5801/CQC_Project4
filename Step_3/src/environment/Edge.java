package environment;

public class Edge 
{
	DirectionVerifier verifier;
	Mover moveDirection;
	
	public Edge(DirectionVerifier verifier, Mover moveDirection)
	{
		this.verifier = verifier;
		this.moveDirection = moveDirection;
	}
}
